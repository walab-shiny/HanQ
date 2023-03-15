import { Box } from '@mui/material';
import ReactApexChart from 'react-apexcharts';

export default function Apex() {
  return (
    <Box width={'calc(8vw)'}>
      <ReactApexChart
        type="donut"
        series={[25, 75]}
        labels={['미참석', '참석']}
        options={{
          chart: {
            height: 10,
            width: 10,
          },
          dataLabels: {
            enabled: false,
          },
          legend: {
            show: false,
          },
          tooltip: {
            enabled: false,
          },
        }}
      />
    </Box>
  );
}
