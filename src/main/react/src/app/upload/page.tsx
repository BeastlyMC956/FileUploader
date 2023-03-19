import EncryptionInput from "../components/EncryptionInput"
import FileInput from "../components/FileInput"

export default function UploadPage() {
  return (
  <div className="w-full h-screen">
    <div className="w-full h-full flex items-center justify-center">
      <div className="flex flex-col items-center text-center w-1/4 h-1/2">
        <form className="w-full h-full flex flex-col justify-around">
          <FileInput />
          <EncryptionInput />
          
          <input type="submit" className="pt-4 pb-4 border-amber-600 hover:border-b hover:-mb-[1px]" />
        </form>
      </div>
    </div>
  </div>
    )
}