from machine import Pin, ADC
from time import sleep

import network
from umqtt.simple import MQTTClient

def do_connect(ssid, password):
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    if not wlan.isconnected():
        print('connecting to network...')
        wlan.connect(ssid, password)
        while not wlan.isconnected():
            pass

print('connected')


do_connect("IoTLab", "hello.world")
photoPIN = 27
sensitivity = 10

sslparams = {'server_hostname': '15e27783f61d43cdb43210b50caf5683.s1.eu.hivemq.cloud'}


def readLight(photoGP):
    photoRes = ADC(Pin(27))
    light = photoRes.read_u16()
    light = round(light/65535*1000)
    return light


mqtt = MQTTClient('client-unique',server='15e27783f61d43cdb43210b50caf5683.s1.eu.hivemq.cloud', port=8883,user='admin', password='adminadmin', keepalive=3600, ssl=True, ssl_params=sslparams)
mqtt.connect()

# message = str(readLight(photoPIN))
# mqtt.publish('my/test/topic', message)
# print('send message: ' + message)
# sleep(5)
# 
# message = str(readLight(photoPIN))
# mqtt.publish('my/test/topic', message)
# print('send message: ' + message)
# sleep(5)
# 
# while True:
#     message = str(readLight(photoPIN))
#     try:
#         mqtt.publish('my/test/topic', message)
#         print('send message: ' + message)
#     except:
#         do_connect("IoTLab", "hello.world")
#         mqtt.connect()
#         print('connecting to network...')
#         continue
#     sleep(5)
#    
reference_light_level = readLight(photoPIN)
message = str(reference_light_level)
print("first readed value is "+message)
mqtt.publish('my/test/topic', message)

check = 0;



while True:
    new_light_level = readLight(photoPIN)
    if(abs(reference_light_level - new_light_level) >= sensitivity):
        try:
            message = str(new_light_level)
            mqtt.publish('my/test/topic',message)
            print("send value: "+message)
            reference_light_level = new_light_level
            sleep(2)
        except:
            print("lost connection, reconnect...")
            do_connect("IoTLab", "hello.world")
            mqtt.connect()
            continue
    else:
        check = check + 1
        sleep(1)
    if (check == 240):
        check = 0
        try:
            message = str(new_light_level)
            mqtt.publish('my/test/topic',message)
            print("send value: "+message)
            reference_light_level = new_light_level
            sleep(2)
        except:
            print("lost connection, reconnect...")
            do_connect("IoTLab", "hello.world")
            mqtt.connect()
            continue
        
    
