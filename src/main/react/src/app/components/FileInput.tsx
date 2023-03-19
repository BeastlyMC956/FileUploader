'use client';

import { useState } from "react";
import boxTransparent from "../public/box-transparent.svg"
import box from "../public/box.svg"

export default function FileInput() {

  const [file, setFile] = useState<File | null>(null);
  const [errorMessage, setErrorMessage] = useState('');

  const handleFileChange = (event: React.ChangeEvent) => {
    event.preventDefault();
    const inputField = event.target as HTMLInputElement;
    if(inputField.files === undefined || inputField.files === null) { return; }
    const selectedFile = inputField.files[0];
    if (selectedFile && selectedFile.size <= 10485760) { // 10 MB in bytes
      setFile(selectedFile);
      setErrorMessage('');
      document.getElementsByClassName("file_name")[0].textContent = selectedFile.name;
      const image = document.getElementById('boxIcon') as HTMLImageElement;
      image.src = box;
    } else {
      setFile(null);
      setErrorMessage('File size must be less than 10MB');
    }
  };

  return (
    <div className="h-1/2 border border-dashed border-black">
      <label htmlFor="file-upload" className="h-full w-full flex flex-col items-center justify-around hover:cursor-pointer">
        Upload a file to encrypt
        <span className="file_name">No File</span>
        <img id='boxIcon' src={boxTransparent} alt="" width={64} height={64}></img>
      </label>
      <input id="file-upload" type="file" name="file" className="hidden" onChange={handleFileChange}></input>
      {errorMessage && <p style={{ color: 'red', margin: '1.5%'}}>{errorMessage}</p>}
    </div>
  )
}