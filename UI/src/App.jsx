import React, { useEffect, useState } from "react";
import { Routes, Route, useNavigate, useParams } from "react-router-dom";

// Add these styles to your CSS file or in a <style> tag in your index.html
const styles = `
  .job-app {
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
    background-color: #f8fafc;
    min-height: 100vh;
    color: #334155;
  }

  .container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 24px;
  }

  .header {
    margin-bottom: 32px;
  }

  .header h1 {
    font-size: 2rem;
    font-weight: 700;
    color: #1e293b;
    margin-bottom: 8px;
  }

  .header p {
    color: #64748b;
    font-size: 1rem;
  }

  .card {
    background: white;
    border-radius: 12px;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
    border: 1px solid #e2e8f0;
    overflow: hidden;
    margin-bottom: 32px;
  }

  .card-header {
    padding: 20px 24px;
    border-bottom: 1px solid #e2e8f0;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #f8fafc;
  }

  .card-title {
    font-size: 1.25rem;
    font-weight: 600;
    color: #1e293b;
  }

  .btn {
    display: inline-flex;
    align-items: center;
    padding: 10px 16px;
    border-radius: 8px;
    font-weight: 500;
    font-size: 0.875rem;
    text-decoration: none;
    border: none;
    cursor: pointer;
    transition: all 0.2s;
    gap: 8px;
  }

  .btn-primary {
    background-color: #3b82f6;
    color: white;
  }

  .btn-primary:hover {
    background-color: #2563eb;
    transform: translateY(-1px);
  }

  .btn-success {
    background-color: #10b981;
    color: white;
  }

  .btn-success:hover {
    background-color: #059669;
  }

  .btn-secondary {
    background-color: #e2e8f0;
    color: #475569;
  }

  .btn-secondary:hover {
    background-color: #cbd5e1;
  }

  .table-container {
    overflow-x: auto;
  }

  .styled-table {
    width: 100%;
    border-collapse: collapse;
  }

  .styled-table th {
    background-color: #f8fafc;
    padding: 12px 24px;
    text-align: left;
    font-weight: 600;
    font-size: 0.75rem;
    text-transform: uppercase;
    letter-spacing: 0.05em;
    color: #64748b;
    border-bottom: 1px solid #e2e8f0;
  }

  .styled-table td {
    padding: 16px 24px;
    border-bottom: 1px solid #f1f5f9;
    vertical-align: top;
  }

  .styled-table tbody tr:hover {
    background-color: #f8fafc;
  }

  .job-link {
    color: #3b82f6;
    text-decoration: none;
    font-weight: 500;
  }

  .job-link:hover {
    color: #1d4ed8;
    text-decoration: underline;
  }

  .code-block {
    background-color: #f1f5f9;
    padding: 8px 12px;
    border-radius: 6px;
    font-family: 'Monaco', 'Menlo', monospace;
    font-size: 0.875rem;
    color: #1e293b;
    border: 1px solid #e2e8f0;
    max-width: 300px;
    max-height: 100px;
    overflow: auto;
    white-space: pre-wrap;
    word-break: break-all;
  }

  .status-badge {
    display: inline-flex;
    align-items: center;
    padding: 4px 12px;
    border-radius: 20px;
    font-size: 0.75rem;
    font-weight: 600;
    text-transform: uppercase;
    letter-spacing: 0.025em;
  }

  .status-running {
    background-color: #dcfce7;
    color: #166534;
  }

  .status-completed {
    background-color: #dbeafe;
    color: #1e40af;
  }

  .status-pending {
    background-color: #fef3c7;
    color: #92400e;
  }

  .empty-state {
    text-align: center;
    padding: 48px 24px;
    color: #64748b;
  }

  .empty-icon {
    width: 48px;
    height: 48px;
    color: #cbd5e1;
    margin: 0 auto 16px auto;
    display: block;
  }

  .form-container {
    max-width: 600px;
    margin: 0 auto;
  }

  .form-group {
    margin-bottom: 24px;
  }

  .form-label {
    display: block;
    font-weight: 500;
    color: #374151;
    margin-bottom: 8px;
    font-size: 0.875rem;
  }

  .form-input,
  .form-textarea,
  .form-select {
    width: 100%;
    padding: 12px 16px;
    border: 1px solid #d1d5db;
    border-radius: 8px;
    font-size: 0.875rem;
    transition: border-color 0.2s, box-shadow 0.2s;
  }

  .form-input:focus,
  .form-textarea:focus,
  .form-select:focus {
    outline: none;
    border-color: #3b82f6;
    box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
  }

  .form-textarea {
    resize: vertical;
    min-height: 120px;
    font-family: 'Monaco', 'Menlo', monospace;
  }

  .form-help {
    margin-top: 4px;
    font-size: 0.75rem;
    color: #6b7280;
  }

  .form-actions {
    display: flex;
    gap: 12px;
    padding-top: 16px;
  }

  .form-actions .btn {
    flex: 1;
  }

  .back-link {
    display: inline-flex;
    align-items: center;
    color: #64748b;
    text-decoration: none;
    font-size: 0.875rem;
    margin-bottom: 24px;
    gap: 4px;
  }

  .back-link:hover {
    color: #1e293b;
  }

  .icon-sm {
    width: 16px;
    height: 16px;
  }
`;

// ======================== HOME PAGE ========================
function HomePage() {
  const [data, setData] = useState([]);
  const [failedJobs, setFailedJobs] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch all jobs
    fetch("http://localhost:8080/api/getAllJobs")
      .then((res) => res.json())
      .then((json) => setData(json))
      .catch((err) => console.error("Error fetching data:", err));

    // Fetch failed jobs
    fetch("http://localhost:8080/api/getAllFailedJobs")
      .then((res) => res.json())
      .then((json) => setFailedJobs(json))
      .catch((err) => console.error("Error fetching failed jobs:", err));
  }, []);

  const getStatusBadgeClass = (status) => {
    switch (status) {
      case 'Running':
        return 'status-badge status-running';
      case 'Completed':
        return 'status-badge status-completed';
      default:
        return 'status-badge status-pending';
    }
  };

  return (
    <div className="job-app">
      <div className="container">
        <div className="header">
          <h1>Job Management Dashboard</h1>
          <p>Manage and monitor your scheduled jobs</p>
        </div>

        <div className="card">
          <div className="card-header">
            <h2 className="card-title">Active Jobs</h2>
            <button
              onClick={() => navigate("/add")}
              className="btn btn-primary"
            >
              <svg className="icon-sm" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4v16m8-8H4" />
              </svg>
              Add New Job
            </button>
          </div>

          <div className="table-container">
            <table className="styled-table">
              <thead>
                <tr>
                  <th>Job Name</th>
                  <th>Cron Expression</th>
                  <th>Work</th>
                  <th>Status</th>
                  <th>Dependency</th>
                </tr>
              </thead>
              <tbody>
                {data.length > 0 ? (
                  data.map((item, idx) => (
                    <tr key={idx}>
                      <td>
                        <a
                          href="#"
                          onClick={(e) => {
                            e.preventDefault();
                            navigate(`/update/${encodeURIComponent(item.jobName)}`, {
                              state: { job: item },
                            });
                          }}
                          className="job-link"
                        >
                          {item.jobName}
                        </a>
                      </td>
                      <td>
                        <code className="code-block" style={{padding: '4px 8px', maxWidth: 'none', maxHeight: 'none'}}>
                          {item.jobCron}
                        </code>
                      </td>
                      <td>
                        <div className="code-block">
                          {item.jobWork}
                        </div>
                      </td>
                      <td>
                        <span className={getStatusBadgeClass(item.jobStatus)}>
                          {item.jobStatus}
                        </span>
                      </td>
                      <td>
                        {item.jobDependency || <span style={{color: '#9ca3af'}}>-</span>}
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="5" className="empty-state">
                      <svg className="empty-icon" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v10a2 2 0 002 2h8a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                      </svg>
                      No jobs found
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>

        <div className="card">
          <div className="card-header">
            <h2 className="card-title">Failed Jobs</h2>
          </div>

          <div className="table-container">
            <table className="styled-table">
              <thead>
                <tr>
                  <th>Fail ID</th>
                  <th>Job Name</th>
                  <th>Fail Time</th>
                  <th>Executor</th>
                </tr>
              </thead>
              <tbody>
                {failedJobs.length > 0 ? (
                  failedJobs.map((item, idx) => (
                    <tr key={idx}>
                      <td>{item.failId}</td>
                      <td>{item.jobName}</td>
                      <td>{new Date(item.failTime).toLocaleString()}</td>
                      <td>{item.executor}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4" className="empty-state">
                      <svg className="empty-icon" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                      </svg>
                      No failed jobs found
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

// ======================== ADD JOB PAGE ========================
function AddJobPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    jobName: "",
    jobCron: "",
    jobWork: "",
    jobStatus: "Pending",
    jobDependency: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch("http://localhost:8080/api/addJob", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Failed to add job");
        return res.json();
      })
      .then(() => {
        alert("Job added successfully!");
        navigate("/");
      })
      .catch((err) => alert("Error: " + err.message));
  };

  return (
    <JobForm
      title="Add New Job"
      form={form}
      setForm={setForm}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
    />
  );
}

// ======================== UPDATE JOB PAGE ========================
function UpdateJobPage() {
  const navigate = useNavigate();
  const { jobName } = useParams();
  const jobData = history.state?.usr?.job || null;
  const [form, setForm] = useState(
    jobData || {
      jobName: decodeURIComponent(jobName),
      jobCron: "",
      jobWork: "",
      jobStatus: "Pending",
      jobDependency: "",
    }
  );

  useEffect(() => {
    if (!jobData) {
      fetch("http://localhost:8080/api/getAllJobs")
        .then((res) => res.json())
        .then((jobs) => {
          const job = jobs.find((j) => j.jobName === decodeURIComponent(jobName));
          if (job) setForm(job);
        });
    }
  }, [jobData, jobName]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch("http://localhost:8080/api/updateJob", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Failed to update job");
        return res.json();
      })
      .then(() => {
        alert("Job updated successfully!");
        navigate("/");
      })
      .catch((err) => alert("Error: " + err.message));
  };

  return (
    <JobForm
      title={`Update Job: ${decodeURIComponent(jobName)}`}
      form={form}
      setForm={setForm}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
    />
  );
}

// ======================== REUSABLE FORM COMPONENT ========================
function JobForm({ title, form, handleChange, handleSubmit }) {
  const navigate = useNavigate();

  return (
    <div className="job-app">
      <div className="container">
        <div className="form-container">
          <a href="#" className="back-link" onClick={(e) => { e.preventDefault(); navigate("/"); }}>
            <svg className="icon-sm" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 19l-7-7 7-7" />
            </svg>
            Back to Jobs
          </a>

          <div className="header">
            <h1>{title}</h1>
          </div>

          <div className="card">
            <div style={{ padding: "24px" }}>
              <form onSubmit={handleSubmit}>
                <div className="form-group">
                  <label className="form-label">Job Name</label>
                  <input
                    type="text"
                    name="jobName"
                    value={form.jobName}
                    onChange={handleChange}
                    required
                    className="form-input"
                    placeholder="Enter job name"
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Cron Expression</label>
                  <input
                    type="text"
                    name="jobCron"
                    value={form.jobCron}
                    onChange={handleChange}
                    required
                    className="form-input"
                    placeholder="e.g., 0 9 * * 1-5"
                    style={{ fontFamily: 'Monaco, Menlo, monospace' }}
                  />
                  <div className="form-help">
                    Use standard cron format: minute hour day month weekday
                  </div>
                </div>

                <div className="form-group">
                  <label className="form-label">Job Work (JSON)</label>
                  <textarea
                    name="jobWork"
                    value={form.jobWork}
                    onChange={handleChange}
                    rows="6"
                    required
                    className="form-textarea"
                    placeholder="Enter job configuration in JSON format"
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Job Status</label>
                  <select
                    name="jobStatus"
                    value={form.jobStatus}
                    onChange={handleChange}
                    className="form-select"
                  >
                    <option value="Pending">Pending</option>
                    <option value="Running">Running</option>
                    <option value="Completed">Completed</option>
                  </select>
                </div>

                <div className="form-group">
                  <label className="form-label">Job Dependency</label>
                  <input
                    type="text"
                    name="jobDependency"
                    value={form.jobDependency}
                    onChange={handleChange}
                    className="form-input"
                    placeholder="Enter job dependency (optional)"
                  />
                </div>

                <div className="form-actions">
                  <button type="submit" className="btn btn-success">
                    <svg className="icon-sm" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                    </svg>
                    Save Job
                  </button>
                  <button
                    type="button"
                    onClick={() => navigate("/")}
                    className="btn btn-secondary"
                  >
                    Cancel
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

// ======================== APP ROUTES ========================
function App() {
  return (
    <>
      <style>{styles}</style>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/add" element={<AddJobPage />} />
        <Route path="/update/:jobName" element={<UpdateJobPage />} />
      </Routes>
    </>
  );
}

export default App;