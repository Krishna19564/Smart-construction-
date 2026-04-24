class SmartLogisticsApp {
    constructor() {
        this.API_BASE = 'http://localhost:8080/api/deliveries';
        this.init();
    }

    init() {
        this.loadDashboardStats();
        this.loadDeliveries();
        this.setupForm();
        this.refreshData();
    }

    async apiCall(endpoint, options = {}) {
        try {
            const response = await fetch