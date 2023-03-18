import React, { useEffect, useState } from "react";
import downloadIcon from "./icons/download.svg";

function GetFiles() {
    /* Setting the initial state of the data. */
    const [data, setData] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/api/v1/encrypted_files/all')
        .then((response) => response.json())
        .then((jsonData) => setData(jsonData))
    }, []);


    return (
        <div className="allFiles">
            Files <br/>
            <ul>
                {data.map((item, index) => (
                    <li className='file' key={index}>
                        <h2>{item.id}</h2>
                        <p>{item.fileName}</p>
                        <button> 
                            <img src={downloadIcon} alt="" width={30} height={30} color="#FFF"></img>
                        </button>
                        
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default GetFiles