import React, {useState} from "react";
import axios from "axios";

const Moving = () => {
    const [value, setValue] = useState();
    const [address, setAddress] = useState();

    const moveBlinds = () => {
        axios({
            method: "post",
            url: "/",
            data: {
                value: value,
                address: address
            }
        })
    }

    return (
        <div>
            <p>move</p>
        </div>
    )

}

export default Moving;