import { Box, Typography } from '@mui/material';
import Logo from '../components/Dashboard/Logo';
import MenuList from '../components/Dashboard/MenuList';
import Header from '../components/Dashboard/Header';
import { QrReader } from 'react-qr-reader';
import { useState } from 'react';

export default function QRScan() {
  const [sending, setSending] = useState(false);
  const [data, setData] = useState(null);
  const [selected, setSelected] = useState('environment');
  const [response, setResponse] = useState(null);

  return (
    <>
      <Box display="flex" height="100%">
        <Box
          display="flex"
          flexDirection="column"
          width={'calc(18vw)'}
          sx={{ bgcolor: 'background.paper' }}
        >
          <Logo />
          <MenuList page={1} />
        </Box>
        <Box display="flex" flexDirection="column" flexGrow={1} gap={1}>
          <Header page="QR 스캔" />
          <Box display="flex" justifyContent="center">
            <QrReader
              className="QrReader"
              constraints={{ facingMode: selected }}
              scanDelay={500}
              onResult={(result, error) => {
                if (!!result) {
                  setData(result?.text);
                }
                if (!!error) {
                  console.log('info', error);
                }
              }}
              videoContainerStyle={{
                width: 'calc(40vw)',
                height: 'calc(40vw)',
              }}
              videoStyle={{ width: 'calc(50vw)', height: 'calc(45vw)' }}
              // containerStyle={{ width: 'calc(50vw)', height: 'calc(50vw)' }}
            />
            <Typography>{data}</Typography>
          </Box>
        </Box>
      </Box>
    </>
  );
}
