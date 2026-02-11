
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { BrowserRouter } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import { registerLicense } from '@syncfusion/ej2-base';
import '@syncfusion/ej2-base/styles/material.css';
import '@syncfusion/ej2-react-grids/styles/material.css';

// Register Syncfusion license key
registerLicense('Ngo9BigBOggjHTQxAR8/V1JGaF5cXGpCfExzWmFZfVhgdV9EYVZSRGY/P1ZhSXxVdkRhUH5dcnVWRmVaUUx9XEA=');

createRoot(document.getElementById('root')).render(

  <BrowserRouter>
    <App />
  </BrowserRouter>

)
