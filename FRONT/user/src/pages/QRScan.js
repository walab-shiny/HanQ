import { Box, Button, Dialog, DialogTitle, Typography } from '@mui/material';
import { QrReader } from 'react-qr-reader';
import { useState } from 'react';
import CloseIcon from '@mui/icons-material/Close';
import ScanOverlay from '../components/QR/ScanOverlay';

export default function QRScan(props) {
  const [sending, setSending] = useState(false);
  const [data, setData] = useState(null);
  const [response, setResponse] = useState(null);

  return (
    <Dialog open={props.open} onClose={props.onClose} fullScreen>
      <DialogTitle display="flex" justifyContent="center">
        <Typography variant="h3" mt={3}>
          Scan QR Code
        </Typography>
        <Button
          onClick={() => {
            window.location.reload();
          }}
          sx={{
            position: 'absolute',
            right: 8,
            top: 16,
            color: (theme) => theme.palette.grey[500],
          }}
        >
          <CloseIcon />
        </Button>
      </DialogTitle>
      <Box display="flex" flexDirection="column" alignItems="center">
        <QrReader
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
            width: 500,
            height: 500,
          }}
          videoStyle={{ width: 500, height: 500 }}
          ViewFinder={ScanOverlay}
          // containerStyle={{ width: 500, height: 450 }}
        />
      </Box>
    </Dialog>
  );
}
