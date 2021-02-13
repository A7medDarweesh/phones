import React, { useMemo, useState, useEffect } from "react";
import axios from "axios";
import { useTable } from 'react-table'
import "./App.css";

function Table({ columns, data }) {
  // Use the state and functions returned from useTable to build your UI
  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable({
    columns,
    data,
  })

  // Render the UI for your table
  return (
    <table {...getTableProps()}>
      <thead>
        {headerGroups.map(headerGroup => (
          <tr {...headerGroup.getHeaderGroupProps()}>
            {headerGroup.headers.map(column => (
              <th {...column.getHeaderProps()}>{column.render('Header')}</th>
            ))}
          </tr>
        ))}
      </thead>
      <tbody {...getTableBodyProps()}>
        {rows.map((row, i) => {
          prepareRow(row)
          return (
            <tr {...row.getRowProps()}>
              {row.cells.map(cell => {
                return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
              })}
            </tr>
          )
        })}
      </tbody>
    </table>
  )
}

function App() {
  // here you set a state to tell the component it need to wait
  //  until the result is fetched from the api
  const [loadingData, setLoadingData] = useState(true);
  const [loadingCountries, setLoadingCountries] = useState(true);
  const [selectedCountry, setSelectedCountry] = useState('all');
  const [selectedStatus, setSelectedStatus] = useState('any');

  const columns = useMemo(() => [
    {
      Header: "country",
      accessor: "country",
    },
    {
      Header: "State",
      accessor: "state",
    },
    {
      Header: "Code",
      accessor: "code",
    },
    {
      Header: "Number",
      accessor: "number",
    },
  ]);
  const changeCountryOption = (event) => { 
    setSelectedCountry(event.currentTarget.value);
    setLoadingData(true);
  };
  const changeStatusOption = (event) => { 
    setSelectedStatus(event.currentTarget.value);
    setLoadingData(true);
  };
  const loadTableData = () => { 
    setLoadingData(true);
     axios
    .get(`http://localhost:8080/api/phones?country=${selectedCountry}&status=${selectedStatus}`)
    .then((response) => {
      // check if the data is populated
      setData(response.data);
      // you tell it that you had the result
      setLoadingData(false);
    });
  };
  const [data, setData] = useState([]);
  const [countries, setCountries] = useState([]);
  const statuses=[{label:'Any',value:'any'},{label:'Valid',value:'valid'},{label:'Invalid',value:'invalid'}]
  useEffect(() => {
    async function getData() {
      await loadTableData();
    }
    async function getCountries() {
      await axios
        .get("http://localhost:8080/api/countries")
        .then((response) => {
          
          setCountries(response.data.map(( name ) => ({ label: name, value: name })));
          setLoadingCountries(false);
          
        });
    }
    if(loadingCountries){

      getCountries();
    }
    if (loadingData) {
      // if the result is not ready so you make the axios call
      getData();
    }
  }, [loadingData]);

  return (
    <div className="App">
      <div class='navigation'>
        <div class='counties'>Filter by country <select
      value={selectedCountry}
      onChange={(e) =>changeCountryOption(e)}
    >
      {countries.map(({ label, value }) => (
        <option key={value} value={value}>
          {label}
        </option>
      ))}
    </select>
    </div>
      <div class='status'>Filter by status <select
      value={selectedStatus}
      onChange={(e) =>changeStatusOption(e)}
    >
      {statuses.map(({ label, value }) => (
        <option key={value} value={value}>
          {label}
        </option>
      ))}
    </select>
    </div>
      </div>
      {/* here you check if the state is loading otherwise if you wioll not call that you will get a blank page because the data is an empty array at the moment of mounting */}
      {loadingData ? (
        <p>Loading Please wait...</p>
      ) : (
        <Table columns={columns} data={data} />
      )}
    </div>
  );
}

export default App;