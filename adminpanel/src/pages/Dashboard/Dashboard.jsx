import React, { useEffect, useState } from 'react';
import { getDashboardStats, fetchAllOrders } from '../../services/orderService';
import {
  AccumulationChartComponent, AccumulationSeriesCollectionDirective, AccumulationSeriesDirective,
  Inject, AccumulationLegend, AccumulationTooltip, AccumulationDataLabel, PieSeries,
  ChartComponent, SeriesCollectionDirective, SeriesDirective, Category, Legend, Tooltip, ColumnSeries, DataLabel
} from '@syncfusion/ej2-react-charts';
import { GridComponent, ColumnsDirective, ColumnDirective, Page, Selection, Sort, Filter } from '@syncfusion/ej2-react-grids';

const Dashboard = () => {
  const [stats, setStats] = useState({
    totalRevenue: 0,
    totalOrders: 0,
    averageOrderValue: 0
  });
  const [pieData, setPieData] = useState([]);
  const [barData, setBarData] = useState([]);
  const [recentOrders, setRecentOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadDashboardData();
  }, []);

  const loadDashboardData = async () => {
    try {
      // Fetch stats and all orders in parallel
      const [dashboardStats, allOrders] = await Promise.all([
        getDashboardStats(),
        fetchAllOrders()
      ]);

      setStats(dashboardStats);

      // Process data for Pie Chart (Order Status Distribution)
      const statusCounts = {};
      allOrders.forEach(order => {
        const status = order.orderStatus || 'Unknown';
        statusCounts[status] = (statusCounts[status] || 0) + 1;
      });
      const processedPieData = Object.keys(statusCounts).map(status => ({
        x: status,
        y: statusCounts[status],
        text: `${status}: ${statusCounts[status]}`
      }));
      setPieData(processedPieData);

      // Process data for Bar Chart (Orders by Price Range - Mocking "Sales Trend" as we lack dates)
      // Or we can just mock a "Weekly Sales" for demo purposes since the user wants to see the chart
      const mockWeeklySales = [
        { day: 'Mon', sales: 4000 },
        { day: 'Tue', sales: 3500 },
        { day: 'Wed', sales: 5000 },
        { day: 'Thu', sales: 4500 },
        { day: 'Fri', sales: 6000 },
        { day: 'Sat', sales: 8000 },
        { day: 'Sun', sales: 7000 },
      ];
      setBarData(mockWeeklySales);

      // Recent Orders for Grid
      setRecentOrders(allOrders.slice(0, 10)); // Top 10

      setLoading(false);
    } catch (error) {
      console.error('Error loading dashboard data:', error);
      setLoading(false);
    }
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
    <div className="container-fluid mt-4">
      <h2 className="mb-4">Admin Dashboard</h2>

      {/* Summary Cards */}
      <div className="row mb-4">
        <div className="col-md-4 mb-4">
          <div className="card border-0 shadow-sm h-100">
            <div className="card-body">
              <div className="d-flex justify-content-between align-items-center">
                <div>
                  <h6 className="text-muted mb-2">Total Revenue</h6>
                  <h3 className="mb-0">₹{stats.totalRevenue.toFixed(2)}</h3>
                </div>
                <div className="bg-success bg-opacity-10 p-3 rounded">
                  <i className="bi bi-currency-rupee text-success fs-2"></i>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card border-0 shadow-sm h-100">
            <div className="card-body">
              <div className="d-flex justify-content-between align-items-center">
                <div>
                  <h6 className="text-muted mb-2">Total Orders</h6>
                  <h3 className="mb-0">{stats.totalOrders}</h3>
                </div>
                <div className="bg-primary bg-opacity-10 p-3 rounded">
                  <i className="bi bi-cart-check text-primary fs-2"></i>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card border-0 shadow-sm h-100">
            <div className="card-body">
              <div className="d-flex justify-content-between align-items-center">
                <div>
                  <h6 className="text-muted mb-2">Avg Order Value</h6>
                  <h3 className="mb-0">₹{stats.averageOrderValue.toFixed(2)}</h3>
                </div>
                <div className="bg-info bg-opacity-10 p-3 rounded">
                  <i className="bi bi-graph-up text-info fs-2"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Syncfusion Charts */}
      <div className="row mb-5">
        <div className="col-lg-6 mb-4">
          <div className="card border-0 shadow-sm p-3">
            <h5 className="card-title text-center mb-3">Order Status Distribution</h5>
            <AccumulationChartComponent id='pie-chart' legendSettings={{ visible: true, position: 'Bottom' }} tooltip={{ enable: true }}>
              <Inject services={[AccumulationLegend, PieSeries, AccumulationTooltip, AccumulationDataLabel]} />
              <AccumulationSeriesCollectionDirective>
                <AccumulationSeriesDirective
                  dataSource={pieData}
                  xName='x'
                  yName='y'
                  radius='70%'
                  dataLabel={{ visible: true, name: 'text', position: 'Outside' }}
                >
                </AccumulationSeriesDirective>
              </AccumulationSeriesCollectionDirective>
            </AccumulationChartComponent>
          </div>
        </div>

        <div className="col-lg-6 mb-4">
          <div className="card border-0 shadow-sm p-3">
            <h5 className="card-title text-center mb-3">Weekly Sales (Simulated)</h5>
            <ChartComponent id='charts' primaryXAxis={{ valueType: 'Category' }} primaryYAxis={{ labelFormat: '₹{value}' }} tooltip={{ enable: true }}>
              <Inject services={[ColumnSeries, Category, Legend, Tooltip, DataLabel]} />
              <SeriesCollectionDirective>
                <SeriesDirective dataSource={barData} xName='day' yName='sales' name='Sales' type='Column' marker={{ dataLabel: { visible: true } }}>
                </SeriesDirective>
              </SeriesCollectionDirective>
            </ChartComponent>
          </div>
        </div>
      </div>

      {/* Recent Orders Grid (Optional) */}
      {/* 
      <div className="row">
        <div className="col-12">
            <div className="card border-0 shadow-sm p-3">
                <h5 className="card-title mb-3">Recent Orders</h5>
                 // Add GridComponent here if needed
            </div>
        </div>
      </div> 
      */}

    </div>
  );
};

export default Dashboard;
