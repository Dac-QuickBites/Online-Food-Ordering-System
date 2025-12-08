import React, { useContext, useState } from "react";
import "./SearchPopup.css";
import { StoreContext } from "../../context/StoreContext";

const SearchPopup = ({ onClose }) => {
  const { food_list } = useContext(StoreContext);    // ⬅ Using your real data
  const [query, setQuery] = useState("");

  // Filter matching food items
  const filtered = food_list.filter((item) =>
    item.name.toLowerCase().includes(query.toLowerCase())
  );

  return (
    <div className="search-popup-overlay">
      <div className="search-popup">

        <button className="close-btn" onClick={onClose}>X</button>

        <input
          type="text"
          placeholder="Search for food..."
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />

        <div className="search-results">
          {query.length === 0 ? (
            <p>Type to search...</p>
          ) : filtered.length === 0 ? (
            <p>No results found.</p>
          ) : (
            filtered.map((item) => (
              <div key={item._id} className="search-item">
                
                <img
                  src={item.image}
                  alt={item.name}
                  className="search-item-img"
                />

                <div className="search-info">
                  <p className="search-name">{item.name}</p>
                  <p className="search-price">${item.price}</p>
                </div>

              </div>
            ))
          )}
        </div>

      </div>
    </div>
  );
};

export default SearchPopup;
