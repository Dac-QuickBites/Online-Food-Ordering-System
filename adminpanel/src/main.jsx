import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
import '@syncfusion/ej2-base/styles/material.css';
import '@syncfusion/ej2-grids/styles/material.css';
import { BrowserRouter } from 'react-router-dom';
import { registerLicense } from '@syncfusion/ej2-base';

// Register Syncfusion license key
// REPLACE 'YOUR_KEY_HERE' WITH YOUR ACTUAL SYNCFUSION LICENSE KEY
registerLicense('Ngo9BigBOggjHTQxAR8/V1JGaF5cXGpCfExzWmFZfVhgdV9EYVZSRGY/P1ZhSXxVdkRhUH5dcnVWRmVaUUx9XEA=');


createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
)
