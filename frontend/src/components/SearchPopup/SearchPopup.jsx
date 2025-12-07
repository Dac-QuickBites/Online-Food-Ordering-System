import React, { useState } from "react";
import "./SearchPopup.css";

// Example product list (replace with your API data)
const products = [
  { id: 1, name: "Pizza Margherita" },
  { id: 2, name: "Burger" },
  { id: 3, name: "Pasta Alfredo" },
  { id: 4, name: "French Fries" },
  { id: 5, name: "Chicken Wings" },
];

const SearchPopup = ({ onClose }) => {
  const [query, setQuery] = useState("");

  // Filter products
  const filtered = products.filter((p) =>
    p.name.toLowerCase().includes(query.toLowerCase())
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
          {filtered.length === 0 ? (
            <p>No results found.</p>
          ) : (
            filtered.map((item) => (
              <p key={item.id} className="search-item">{item.name}</p>
            ))
          )}
        </div>

      </div>
    </div>
  );
};

export default SearchPopup;
