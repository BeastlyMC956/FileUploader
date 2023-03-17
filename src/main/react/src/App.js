import logo from './logo.svg';
import './App.css';
import TestAPI from './TestAPI';
import { useState } from 'react';

function PostData() {
  const [formvalue, setFormValue] = useState({fileContent: [], encryptionKey: ''})
  const handleFile = (event) => {
    const {name} = event.target;
    const file = event.target.files[0];;
    const reader = new FileReader();

    reader.onload = function(event) {
      const binaryString = event.target.result;
      const byteArray = new Uint8Array(binaryString.length);

      for (let i = 0; i < binaryString.length; i++) {
        byteArray[i] = binaryString.charCodeAt(i);
      }

      setFormValue({...formvalue, [name]:byteArray})
    };

    reader.readAsBinaryString(file);
  }

  const handleEncryption = (event) => {
    const {name, value} = event.target;
    setFormValue({...formvalue, [name]:value})
  }

  const handleSubmition = (event) => {
    event.preventDefault();
    const fileContent = Array.from(formvalue.fileContent);
    const encryptionKey = formvalue.encryptionKey;
    
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({fileContent: fileContent, encryptionKey: encryptionKey})
    };

    try {
      const response = fetch("http://localhost:8080/api/v1/encrypted_files/add", requestOptions);
      console.log("Upload OK!")
    } catch (error) {
      console.error(error);
    }
  }


  return (
    <div className='post_form'>
      <form onSubmit={handleSubmition}>
        <input type='file' name='fileContent' onChange={handleFile}></input>
        <div className='encryptionDiv'>
          <label>Encryption Key</label>
          <input type='text' name='encryptionKey' onChange={handleEncryption} placeholder='Encryption Key' value={formvalue.encryptionKey}></input>
        </div>
        <input type='submit'></input>
      </form>
    </div>
  )
}

function App() {
  return (
    <div className='App'>
      <PostData />
      <TestAPI />
    </div>
  );
}

export default App;
