import { Box, Button, Dialog, IconButton, Typography } from '@mui/material';
import { QrReader } from 'react-qr-reader';
import { useState } from 'react';
import CloseIcon from '@mui/icons-material/Close';
import ScanOverlay from '../components/QR/ScanOverlay';
import { sendQrRequest } from '../apis/qr';
import { useSnackbar } from 'notistack';

export default function QRScan({ event }) {
  const { enqueueSnackbar } = useSnackbar();
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleOnResult = async (resultString) => {
    const response = await sendQrRequest({ eventId: +event.id, qrString: resultString });
    if (response.status === 200) {
      enqueueSnackbar(`${response.data.name}님 출석처리 되었습니다.`, {
        variant: 'success',
      });
    } else {
      enqueueSnackbar(`오류가 발생했습니다.`, {
        variant: 'error',
      });
    }
    console.log(response);
  };

  return (
    <>
      <Button size="small" variant="contained" color="success" onClick={handleOpen}>
        QR 스캔
      </Button>
      <Dialog open={open} onClose={handleClose} fullScreen>
        <IconButton
          onClick={() => window.location.reload()}
          sx={{
            position: 'absolute',
            right: 16,
            top: 16,
          }}
        >
          <CloseIcon />
        </IconButton>
        <Box
          sx={{
            height: 1,
            display: 'flex',
            flexDirection: 'column',
            justifyContent: 'center',
            alignItems: 'center',
          }}
        >
          <Typography variant="h3">{event.name} 출석 태깅하기</Typography>
          <QrReader
            scanDelay={500}
            onResult={(result) => {
              if (!!result) {
                handleOnResult(result.text);
              }
            }}
            videoContainerStyle={{
              width: 500,
              height: 500,
            }}
            videoStyle={{ width: 500, height: 500 }}
            ViewFinder={ScanOverlay}
          />
        </Box>
      </Dialog>
    </>
  );
}
