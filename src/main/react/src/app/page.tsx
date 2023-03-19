export default function HomePage() {
  return (
  <div className="w-full h-screen">
    <div className="w-full h-full flex items-center justify-center">
      <div className="flex flex-col items-center text-center">
        <div className="p-20">
          <h1 className="text-3xl font-bold pb-3">File Uploader</h1>
          <p className="max-w-3xl text-center">Encryption is no longer a premium, but a standard. With our service, your privacy is our first concern!</p>
        </div>
        <div className="w-52 h-16 bg-amber-600 border-amber-600 shadow-sm shadow-black rounded-md text-center hover:bg-amber-700 hover:cursor-pointer">
            <a href="/upload" className="text-x w-full h-full flex items-center justify-center text-lg font-semibold text-white">Lets Go!</a>
        </div>         
      </div>
    </div>  
  </div>
    )
}