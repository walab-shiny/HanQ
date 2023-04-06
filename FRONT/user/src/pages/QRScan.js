import { Box, Button, Dialog, Typography } from '@mui/material';
import { QrReader } from 'react-qr-reader';
import { useState } from 'react';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';

export default function QRScan(props) {
  const [sending, setSending] = useState(false);
  const [data, setData] = useState(null);
  const [selected, setSelected] = useState('environment');
  const [response, setResponse] = useState(null);

  return (
    <Dialog open={props.open} onClose={props.onClose} fullWidth>
      <Box display="flex" flexDirection="column" alignItems="center">
        <Box display="flex" alignItems="baseline">
          <Button
            onClick={() => {
              window.location.reload();
            }}
          >
            <ArrowBackIosIcon />
          </Button>
          <Typography variant="h3" mt={4}>
            QR 스캔
          </Typography>
        </Box>

        <QrReader
          className="QrReader"
          constraints={{ facingMode: selected }}
          scanDelay={500}
          onResult={(result, error) => {
            if (!!result) {
              setData(result?.text);
              alert(result.text);
            }
            if (!!error) {
              console.log('info', error);
            }
          }}
          videoContainerStyle={{
            width: 'calc(40vw)',
            height: 'calc(46vw)',
          }}
          videoStyle={{ width: 'calc(50vw)', height: 'calc(46vw)' }}
          // containerStyle={{ width: 'calc(50vw)', height: 'calc(50vw)' }}
        />
      </Box>
    </Dialog>
  );
}
