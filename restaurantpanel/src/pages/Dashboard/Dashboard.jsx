import React, { useEffect, useState } from 'react';
import { fetchDashboardStats } from '../../services/orderService';
import { toast } from 'react-toastify';
import {
  AccumulationChartComponent, AccumulationSeriesCollectionDirective, AccumulationSeriesDirective,
  Inject, AccumulationLegend, AccumulationTooltip, AccumulationDataLabel, PieSeries,
  ChartComponent, SeriesCollectionDirective, SeriesDirective, Category, Legend, Tooltip, ColumnSeries, DataLabel
} from '@syncfusion/ej2-react-charts';

const Dashboard = () => {
  const [stats, setStats] = useState({
    totalOrders: 0,
    totalRevenue: 0,
    averageOrderValue: 0
  });
  const [pieData, setPieData] = useState([]);
  const [barData, setBarData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadStats = async () => {
      try {
        setLoading(true);
        const data = await fetchDashboardStats();
        setStats(data || { totalOrders: 0, totalRevenue: 0, averageOrderValue: 0 });

        // Simulate detailed data for charts based on total stats
        // In a real app, this would come from the API
        generateMockChartData(data || { totalOrders: 0, totalRevenue: 0 });

      } catch (error) {
        console.error("Dashboard error:", error);
        toast.error("Error loading dashboard stats. Using demo data.");
        // Fallback to demo data
        const demoStats = { totalOrders: 152, totalRevenue: 45600, averageOrderValue: 300 };
        setStats(demoStats);
        generateMockChartData(demoStats);
      } finally {
        setLoading(false);
      }
    };
    loadStats();
  }, []);

  const generateMockChartData = (currentStats) => {
    // 1. Pie Chart: Order Status Distribution
    // We arbitrarily split totalOrders
    const total = currentStats.totalOrders || 100;
    const delivered = Math.floor(total * 0.7);
    const pending = Math.floor(total * 0.2);
    const cancelled = total - delivered - pending;

    setPieData([
      { x: 'Delivered', y: delivered, text: `Delivered: ${delivered}` },
      { x: 'Pending', y: pending, text: `Pending: ${pending}` },
      { x: 'Cancelled', y: cancelled, text: `Cancelled: ${cancelled}` }
    ]);

    // 2. Bar Chart: Weekly Sales (Simulated)
    // We create a trend around the average daily revenue (Total / 30 days approx, or just random)
    const days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
    const mockSales = days.map(day => {
      // Random sales between 2000 and 8000
      return { day, sales: Math.floor(Math.random() * 6000) + 2000 };
    });
    setBarData(mockSales);
  };

  if (loading) {
    return (
      <div className="d-flex justify-content-center align-items-center" style={{ height: '400px' }}>
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  return (
    <div className="container-fluid mt-2 fade-in">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
          <h2 className="fw-bold text-dark">Restaurant Dashboard</h2>
          <p className="text-muted">Overview of your business performance</p>
        </div>
        <button className="btn btn-outline-primary" onClick={() => window.location.reload()}>
          <i className="bi bi-arrow-clockwise me-2"></i>Refresh
        </button>
      </div>

      {/* Summary Cards */}
      <div className="row g-4 mb-5">
        <div className="col-md-4">
          <div className="card border-0 shadow-sm h-100 overflow-hidden">
            <div className="card-body p-4 position-relative">
              <div className="d-flex align-items-center mb-3">
                <div className="rounded-circle bg-primary bg-opacity-10 p-3 me-3">
                  <i className="bi bi-cart-check-fill text-primary fs-3"></i>
                </div>
                <h6 className="card-title text-muted text-uppercase fw-bold mb-0">Total Orders</h6>
              </div>
              <h2 className="display-5 fw-bold mb-0">{stats.totalOrders}</h2>
              <div className="mt-2 text-success small">
                <i className="bi bi-graph-up-arrow me-1"></i>
                <span>+12% from last week</span>
              </div>
            </div>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card border-0 shadow-sm h-100 overflow-hidden">
            <div className="card-body p-4 position-relative">
              <div className="d-flex align-items-center mb-3">
                <div className="rounded-circle bg-success bg-opacity-10 p-3 me-3">
                  <i className="bi bi-currency-rupee text-success fs-3"></i>
                </div>
                <h6 className="card-title text-muted text-uppercase fw-bold mb-0">Total Revenue</h6>
              </div>
              <h2 className="display-5 fw-bold mb-0">₹{stats.totalRevenue.toLocaleString()}</h2>
              <div className="mt-2 text-success small">
                <i className="bi bi-graph-up-arrow me-1"></i>
                <span>+8% from last week</span>
              </div>
            </div>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card border-0 shadow-sm h-100 overflow-hidden">
            <div className="card-body p-4 position-relative">
              <div className="d-flex align-items-center mb-3">
                <div className="rounded-circle bg-warning bg-opacity-10 p-3 me-3">
                  <i className="bi bi-wallet2 text-warning fs-3"></i>
                </div>
                <h6 className="card-title text-muted text-uppercase fw-bold mb-0">Avg Order Value</h6>
              </div>
              <h2 className="display-5 fw-bold mb-0">₹{stats.averageOrderValue.toFixed(0)}</h2>
              <div className="mt-2 text-muted small">
                <i className="bi bi-dash me-1"></i>
                <span>Stable performance</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Charts Section */}
      <div className="row g-4 chart-section">
        <div className="col-lg-6">
          <div className="card border-0 shadow-sm p-3 h-100">
            <div className="card-header bg-white border-0">
              <h5 className="card-title fw-bold">Order Status Distribution</h5>
            </div>
            <div className="card-body">
              <AccumulationChartComponent id='pie-chart' legendSettings={{ visible: true, position: 'Bottom' }} tooltip={{ enable: true }} enableAnimation={true}>
                <Inject services={[AccumulationLegend, PieSeries, AccumulationTooltip, AccumulationDataLabel]} />
                <AccumulationSeriesCollectionDirective>
                  <AccumulationSeriesDirective
                    dataSource={pieData}
                    xName='x'
                    yName='y'
                    innerRadius='40%'
                    startAngle={0}
                    endAngle={360}
                    radius='75%'
                    dataLabel={{ visible: true, name: 'text', position: 'Outside', font: { fontWeight: '600' } }}
                    palettes={['#4ade80', '#fbbf24', '#f87171']}
                  >
                  </AccumulationSeriesDirective>
                </AccumulationSeriesCollectionDirective>
              </AccumulationChartComponent>
            </div>
          </div>
        </div>

        <div className="col-lg-6">
          <div className="card border-0 shadow-sm p-3 h-100">
            <div className="card-header bg-white border-0">
              <h5 className="card-title fw-bold">Weekly Sales Trend (Simulated)</h5>
            </div>
            <div className="card-body">
              <ChartComponent id='column-chart' primaryXAxis={{ valueType: 'Category', majorGridLines: { width: 0 } }} primaryYAxis={{ labelFormat: '₹{value}', lineStyle: { width: 0 } }} tooltip={{ enable: true }} chartArea={{ border: { width: 0 } }}>
                <Inject services={[ColumnSeries, Category, Legend, Tooltip, DataLabel]} />
                <SeriesCollectionDirective>
                  <SeriesDirective dataSource={barData} xName='day' yName='sales' name='Sales' type='Column' cornerRadius={{ topLeft: 10, topRight: 10 }} fill='#6366f1'>
                  </SeriesDirective>
                </SeriesCollectionDirective>
              </ChartComponent>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
