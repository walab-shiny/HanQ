import * as xlsx from 'xlsx';
import { Button } from '@mui/material';
import FileDownloadIcon from '@mui/icons-material/FileDownload';

function excel(data: any) {
  const jsonData = data;
  const workbook = xlsx.utils.book_new();
  const worksheet = xlsx.utils.json_to_sheet(jsonData);
  xlsx.utils.book_append_sheet(workbook, worksheet, 'sheet1');
  xlsx.writeFile(workbook, 'report.xlsx');
}

interface Props {
  data: any;
}

export default function ExportButton(props: Props) {
  return (
    <>
      <Button style={{ color: 'gray' }} onClick={() => excel(props.data)}>
        <FileDownloadIcon />
      </Button>
    </>
  );
}
