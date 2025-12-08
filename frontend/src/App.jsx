import React from "react";
import Navbar from "./components/Navbar/Navbar";
import { Route, Routes } from "react-router-dom";
import PlaceOrder from "./pages/PlaceOrder/PlaceOrder";
import Home from "./pages/Home/Home";
import Cart from "./components/Cart/Cart";
import Footer from "./components/Footer/Footer";
import ContactUs from "./pages/ContactUs/ContactUs";
import Offers from './components/Offers/Offers'
// import PlaceOrder from './pages/PlaceOrder/PlaceOrder'

const App = () => {
  return (
    <>
      <div className="app">
        <Navbar />

        <Routes>
          <Route path="/" element={<Home />} />

          <Route path="/cart" element={<Cart />} />

          <Route path="/PlaceOrder" element={<PlaceOrder />} />
          <Route path="/contact-us" element={<ContactUs />} />
          <Route path="/offers" element={<Offers />} />

        </Routes>
      </div>
      <Footer />
    </>
  );
};

export default App;
