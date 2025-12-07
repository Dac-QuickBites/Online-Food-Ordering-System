import React, { useContext } from "react";
import "./Cart.css";
import { StoreContext } from "../../context/StoreContext";

const Cart = () => {
  const { cartItems, food_list, addToCart, removeFromCart } =
    useContext(StoreContext);

  const cartProducts = food_list.filter(
    (item) => cartItems[item.id] > 0
  );

  const subtotal = cartProducts.reduce(
    (sum, item) => sum + item.price * cartItems[item.id],
    0
  );

  return (
    <div className="cart">
      <h2>Your Cart</h2>

      <div className="cart-items">
        <div className="cart-header-row">
          <p>Item</p>
          <p>Title</p>
          <p>Price</p>
          <p>Qty</p>
          <p>Total</p>
          <p>Remove</p>
        </div>

        <hr />

        {cartProducts.length === 0 && <p>Your cart is empty.</p>}

        {cartProducts.map((item) => (
          <div key={item.id}>
            <div className="cart-row">
              <img src={item.image} alt={item.name} className="cart-img" />

              <p>{item.name}</p>
              <p>${item.price}</p>

              <div className="qty-controls">
                <button onClick={() => removeFromCart(item.id)}>-</button>
                <span>{cartItems[item.id]}</span>
                <button onClick={() => addToCart(item.id)}>+</button>
              </div>

              <p>${(item.price * cartItems[item.id]).toFixed(2)}</p>

              <button
                className="remove-btn"
                onClick={() => removeFromCart(item.id)}
              >
                X
              </button>
            </div>

            <hr />
          </div>
        ))}
      </div>

      <div className="cart-summary">
        <h2>Bill Summary</h2>

        <div className="summary-row">
          <p>Subtotal</p>
          <p>${subtotal.toFixed(2)}</p>
        </div>

        <div className="summary-row">
          <p>Delivery Fee</p>
          <p>$2.00</p>
        </div>

        <div className="summary-row total">
          <p>Total</p>
          <p>${(subtotal + 2).toFixed(2)}</p>
        </div>

        <button className="checkout-btn">Proceed To Checkout</button>
      </div>
    </div>
  );
};

export default Cart;
