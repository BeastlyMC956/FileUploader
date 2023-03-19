'use client';

import generateIcon from "../public/repeat.svg"

export default function EncryptionInput() {
  const generateString = (event: React.MouseEvent) => {
    event.preventDefault();
    const length = 32;
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';

    for (let i = 0; i < length; i++) {
      result += characters.charAt(Math.floor(Math.random() * characters.length));
    }

    const inputElement = document.getElementById("encryptionField") as HTMLInputElement;
    inputElement.value = result;
  }
  return (
    <div className="flex justify-between items-center h-16">
      <label>Encryption Key</label>
      <input type="text" name="encryptionKey" id="encryptionField" className="w-3/4 h-full text-lg border-b border-black"></input>
      <button onClick={generateString}>
          <img alt="" src={generateIcon} width={25} height={25} title="Generate a new encryption key" />
      </button>
    </div>
  )
}