import React, { useContext } from "react";
import { StoreContext } from "../../context/StoreContext";
import "./Cart.css";

const Cart = () => {
  const { cartItems, food_list, addToCart, removeFromCart } = useContext(StoreContext);

  // Filter only products whose quantity is > 0
  const cartProducts = food_list.filter((item) => cartItems[item._id] > 0);

  // Calculate total price
  const totalPrice = cartProducts.reduce((sum, item) => {
    return sum + item.price * cartItems[item._id];
  }, 0);

  return (
    <div className="cart-page">
      <h2>Your Cart</h2>

      {cartProducts.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          <div className="cart-items">
            {cartProducts.map((item) => (
              <div className="cart-item" key={item._id}>
                <img src={item.image} alt={item.name} className="cart-item-img" />

                <div className="cart-details">
                  <h3>{item.name}</h3>
                  <p>${item.price}</p>

                  {/* Quantity Control */}
                  <div className="cart-controls">
                    <button onClick={() => removeFromCart(item._id)}>-</button>

                    <span>{cartItems[item._id]}</span>

                    <button onClick={() => addToCart(item._id)}>+</button>
                  </div>
                </div>
              </div>
            ))}
          </div>

          {/* Total Section */}
          <div className="cart-total">
            <h3>Total: ${totalPrice.toFixed(2)}</h3>
            <button className="checkout-btn">Checkout</button>
          </div>
        </>
      )}
    </div>
  );
};

export default Cart;
