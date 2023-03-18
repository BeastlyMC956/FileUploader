import lightThemeIcon from "../icons/himoon.svg"
function Header() {
  //const [isDarkTheme, setIsDarkTheme] = useState(false);

  //const toggleTheme = () => {
    //setIsDarkTheme(!isDarkTheme);
    // code to switch between light and dark themes
  //};

  return (
    <nav className="navbar">
        <div className="lhs">
            <ul className="navUl">
                <li>ICON</li>
                <li>Home</li>
                <li>Pricing</li>
                <li>Help</li>
            </ul>
        </div>

        <div className="rhs">
            <ul className="navUl">
                <li>
                    <img src={lightThemeIcon} width={16} height={16} alt=""/>
                </li>
                <li>Sign Up</li>
                <li>Sign In</li>
            </ul>
        </div>
    </nav>
  );
}

export default Header
