import React, { useState } from "react";
import {useContext} from 'react'
import "./Navbar.css";
import { Link } from "react-router-dom";
import { assets } from "../../assets/assets";
import SearchPopup from '../SearchPopup/SearchPopup'
import { StoreContext } from "../../context/StoreContext";
const Navbar = () => {
  const [menu, setMenu] = useState("menu");
  const [showSearch, setShowSearch] = useState(false);
  const { cartItems } = useContext(StoreContext);
  const cartCount = Object.values(cartItems).reduce((a, b) => a + b, 0);
  return (
    <div className="navbar">
      <Link to="/">
        <img src={assets.logo} alt="" className="logo" />
      </Link>
      <ul className="navbar-menu">
        <Link to="/">
          <li
            onClick={() => setMenu("home")}
            className={menu === "home" ? "active" : ""}
          >
            home
          </li>
        </Link>
        <li
          onClick={() => setMenu("menu")}
          className={menu === "menu" ? "active" : ""}
        >
          menu
        </li>
        <li
          onClick={() => setMenu("mobile-app")}
          className={menu === "mobile-app" ? "active" : ""}
        >
          mobile-app
        </li>
        <li
          onClick={() => setMenu("contact-us")}
          className={menu === "contact-us" ? "active" : ""}
        >
          <Link to = '/contact-us'>
          contact us
          </Link>
        </li>
      </ul>
      <div className="navbar-right">
        <img src={assets.search_icon} alt="" onClick={()=>setShowSearch(true)}/>
        <div className="navbar-search-icon">
          <Link to="/cart">
            <img src={assets.basket_icon} alt="" />
            {cartCount > 0 && <span className="cart-count">{cartCount}</span>}
          </Link>
          <div className="dot"></div>
        </div>
        <button>sign in</button>
      </div>
      {showSearch && <SearchPopup onClose={() => setShowSearch(false)} />}
    </div>
  );
};

export default Navbar;
