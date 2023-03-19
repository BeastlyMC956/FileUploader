import '../styles/globals.css';

export default function RootLayout({
    children,
}: {
    children: React.ReactNode
}) {
  const hoverButton = "p-4 w-28 text-center border-amber-600 transition duration-150 ease-in hover:border-b-[1px] hover:-mb-[1px] hover:ease-in p-4 hover:cursor-pointer";
  return (
  <html>
    <head></head>
      <body>
        <nav className="flex justify-between items-center h-16">
          <ul className="flex w-1/3 justify-around items-center h-full">
            <a href='/'><li className={hoverButton}>Icon</li></a>
            <a href='/'><li className={hoverButton}>Home</li></a>
            <a href='/pricing'><li className={hoverButton}>Pricing</li></a>
            <li className={hoverButton}>Help</li>
          </ul>
          <ul className="flex w-1/4 justify-around items-center h-full">
            <li className={hoverButton}>Theme</li>
            <li className={hoverButton}>Sign Up</li>
            <li className="bg-amber-600 rounded-md text-white font-bold p-4 pr-6 pl-6 text-center hover:bg-amber-700 hover:cursor-pointer">Sign In</li>
          </ul>
        </nav>
            {children}
      </body>
  </html>
  );
}