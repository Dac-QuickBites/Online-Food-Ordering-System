import React from "react";
import "./Offers.css";
import { menu_list } from "../../assets/assets"; 
import { offerData } from "../../assets/offerData"; // importing your new json file

const Offers = () => {
  return (
    <div className="offers-page">
      <h2>🔥 Special Offers For You</h2>

      <div className="offers-grid">
        {offerData.map((offer) => {
          // Find matching category image
          const categoryItem = menu_list.find(
            (item) => item.menu_name === offer.category
          );

          return (
            <div className="offer-card" key={offer.id}>
              <img
                src={categoryItem ? categoryItem.menu_image : ""}
                alt={offer.title}
                className="offer-img"
              />

              <h3>{offer.title}</h3>

              <p className="coupon-text">
                Use Code: <strong>{offer.code}</strong>
              </p>

              <button
                className="offer-btn"
                onClick={() => alert(`Coupon Applied: ${offer.code}`)}
              >
                Apply Offer
              </button>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default Offers;
