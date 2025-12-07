import React, { useState } from "react";
import "./ContactUs.css";

const ContactUs = () => {
  const [loading, setLoading] = useState(false);
  const [submitted, setSubmitted] = useState(false);

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);

    // simulate sending data to backend
    setTimeout(() => {
      setLoading(false);
      setSubmitted(true);

      // clear input fields after submit
      setName("");
      setEmail("");
      setMessage("");

      // hide success message after 5 sec
      setTimeout(() => setSubmitted(false), 5000);
    }, 0);
  };

  return (
    <div className="contact-wrapper">
      <div className="contact-box">
        <h1 className="contact-heading">Contact Us</h1>
        <p className="contact-subtext">
          We’d love to hear from you. Reach out with any questions!
        </p>

        {submitted && (
          <div className="success-message">
            Your message has been sent successfully! 🎉
          </div>
        )}

        <form className="contact-form" onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Your Name"
            required
            disabled={loading}
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          <input
            type="email"
            placeholder="Your Email"
            required
            disabled={loading}
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <textarea
            placeholder="Your Message"
            required
            disabled={loading}
            value={message}
            onChange={(e) => setMessage(e.target.value)}
          ></textarea>

          <button type="submit" disabled={loading}>
            {loading ? <div className="spinner"></div> : "Send Message"}
          </button>
        </form>
      </div>
    </div>
  );
};

export default ContactUs;
