import React, { useEffect, useState } from "react";

function TestAPI() {
    /* Setting the initial state of the data. */
    const [data, setData] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/api/v1/encrypted_files/all')
        .then((response) => response.json())
        .then((jsonData) => setData(jsonData))
    }, []);

    return (
        <div>
            Files <br/>
            <ul>
                {data.map((item, index) => (
                    <li className='file' key={index}>
                        <h2>{item.id}</h2>
                        <p>{item.filePath}</p>
                    </li>
                ))}
            </ul>
        </div>
    )

    
}

export default TestAPI