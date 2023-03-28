import * as xlsx from 'xlsx';
import { Button } from '@mui/material';
import FileDownloadIcon from '@mui/icons-material/FileDownload';

function excel() {
  const jsonData = [
    {
      번호: 1,
      작성자: '김한동',
      내용: '실제 개발자들이 어떻게 일하는 지에 대해 알 수 있는 시간이었습니다.',
      수정일: '2023-03-29',
    },
    {
      번호: 2,
      작성자: '박한동',
      내용: '실제 개발자들이 어떻게 일하는 지에 대해 알 수 있는 시간이었습니다.',
      수정일: '2023-03-29',
    },
  ];
  const workbook = xlsx.utils.book_new();
  const worksheet = xlsx.utils.json_to_sheet(jsonData);
  xlsx.utils.book_append_sheet(workbook, worksheet, 'sheet1');
  xlsx.writeFile(workbook, 'report.xlsx');
}

export default function ExportButton() {
  return (
    <>
      <Button style={{ color: 'gray' }} onClick={excel}>
        <FileDownloadIcon />
      </Button>
    </>
  );
}
