import React, { useContext } from "react";
import { StoreContext } from "../../context/StoreContext";
import "./Cart.css";

const Cart = () => {
  const { cartItems, food_list, addToCart, removeFromCart } = useContext(StoreContext);

  // Convert cartItems into array of products
  const cartProducts = Object.entries(cartItems)
    .filter(([id, qty]) => qty > 0)                   // Only items with qty > 0
    .map(([id, qty]) => {
      // id comes as NUMBER, convert everything to string for matching
      const product = food_list.find(item => item._id === id.toString());

      if (product) {
        return { ...product, quantity: qty };
      }
      return null;
    })
    .filter(Boolean);  // Remove nulls

  const totalPrice = cartProducts.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

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

                  <div className="cart-controls">
                    <button onClick={() => removeFromCart(item._id)}>-</button>
                    <span>{item.quantity}</span>
                    <button onClick={() => addToCart(item._id)}>+</button>
                  </div>
                </div>

                <div className="cart-item-total">
                  <p>${(item.price * item.quantity).toFixed(2)}</p>
                </div>
              </div>
            ))}
          </div>

          <div className="cart-total">
            <h3>Total Price: ${totalPrice.toFixed(2)}</h3>
            <button className="checkout-btn">Checkout</button>
          </div>
        </>
      )}
    </div>
  );
};

export default Cart;
