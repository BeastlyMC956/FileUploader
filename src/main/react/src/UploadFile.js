import { useState } from "react";
import uploadIcon from "./icons/arrow-up.svg";
import generateIcon from "./icons/repeat.svg"

function UploadFile() {
    const [file, setFile] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');
    const handleSubmit = (event) => {
        event.preventDefault();
        const form = event.target;
        const formData = new FormData(form);
        formData.set('fileName', form.file.files[0].name);
        const fileReader = new FileReader();
        fileReader.readAsArrayBuffer(form.file.files[0]); 
        fileReader.onload = () => {
            const fileContent = new Uint8Array(fileReader.result);
            const jsonData = {
                fileName: formData.get('fileName'),
                fileContent: Array.from(fileContent),
                encryptionKey: formData.get('encryptionKey'),
            };
            const url = 'http://localhost:8080/api/v1/encrypted_files/upload';
            fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(jsonData),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        };
    }

    const generateString = (event) => {
      event.preventDefault();
      const length = 32;
      const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
      let result = '';

      for (let i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
      }
      document.getElementById("encryptionField").value = result;
    }

    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        if (selectedFile && selectedFile.size <= 10485760) { // 10 MB in bytes
          setFile(selectedFile);
          setErrorMessage('');
          document.getElementsByClassName("file_name")[0].textContent = selectedFile.name;
        } else {
          setFile(null);
          setErrorMessage('File size must be less than 10MB');
        }
      };
  
  
    return (
    <div className='post_form'>
        <form onSubmit={handleSubmit}>
            <div id="uploadDiv">
                <label htmlFor="file-upload">
                    Upload a file to encrypt
                    <span className="file_name">No File</span>
                    <img src={uploadIcon} alt="" width={50} height={50} />
                </label>
                <input id="file-upload" type='file' name='file' onChange={handleFileChange} />
                {errorMessage && <p style={{ color: 'red', margin: '1.5%'}}>{errorMessage}</p>}
          </div>
          
          
        <div className='encryptionDiv'>
            <label>Encryption Key</label>
            <input type='text' id="encryptionField" name='encryptionKey' />
            <button onClick={generateString}>
                <img src={generateIcon} alt="" width={25} height={25} title="Generate a new encryption key" />
            </button>
          </div>
          <input type='submit'></input>
        </form>
      </div>
    )
}

export default UploadFile