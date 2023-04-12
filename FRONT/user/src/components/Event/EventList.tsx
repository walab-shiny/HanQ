import { Button, Chip, Toolbar, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import AddEventDialog from './AddEventDialog';
import ReportDialog from '../Report/ReportDialog';
import { getEventList } from '../../apis/event';
import { IEvent } from '../../types/event';
import QRScan from '../../pages/QRScan';
import { DataGrid, GridRenderCellParams, GridToolbar } from '@mui/x-data-grid';
import CustomNoRowsOverlay from '../common/CustomNoRowsOverlay';
import { ITag } from '../../types/tag';
import { getDisplayTime } from '../../utils/functions';

function RenderTag(props: GridRenderCellParams<any, ITag[]>) {
  const { value } = props;

  return (
    <>
      {value?.map((tag) => (
        <Chip key={tag.id} label={tag.name} sx={{ mr: 1 }} size="small" />
      ))}
    </>
  );
}

const titles = [
  { field: 'index', headerName: '번호', width: 64 },
  { field: 'tags', headerName: '태그', flex: 1, renderCell: RenderTag },
  { field: 'name', headerName: '제목', width: 150 },
  { field: 'openAt', headerName: '일자', width: 200 },
  { field: 'location', headerName: '장소' },
  { field: 'maxUsers', headerName: '참여인원수' },
];

export default function EventList() {
  const navigate = useNavigate();
  const [eventList, setEventList] = useState<IEvent[]>([]);
  const [reportOpen, setReportOpen] = useState(false);
  const handleReportOpen = () => setReportOpen(true);
  const handleReportClose = () => setReportOpen(false);

  const fetchData = async () => {
    const response = await getEventList();
    setEventList(response);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
        <Typography variant="subtitle1" m={1}>
          주최 이벤트 목록
        </Typography>
        <AddEventDialog fetchData={fetchData} />
      </Toolbar>
      <DataGrid
        sx={{
          backgroundColor: 'background.paper',
          border: 0,
          borderRadius: 1,
          boxShadow: 'rgb(0 0 0 / 4%) 0px 0px 8px',
          height: '75vh',
        }}
        components={{
          Toolbar: GridToolbar,
          NoRowsOverlay: CustomNoRowsOverlay,
        }}
        componentsProps={{
          toolbar: {
            showQuickFilter: true,
            quickFilterProps: { debounceMs: 500 },
            printOptions: { disableToolbarButton: true },
          },
        }}
        rows={eventList.map((event, index) => {
          return {
            ...event,
            index: index + 1,
            openAt: getDisplayTime(event.openAt),
          };
        })}
        columns={[
          ...titles,
          {
            field: 'actions',
            type: 'actions',
            headerName: '기능',
            width: 240,
            cellClassName: 'actions',
            getActions: ({ row }) => {
              return [
                <Button
                  size="small"
                  variant="contained"
                  color="inherit"
                  onClick={() => navigate(`/event/detail/${row.id}`)}
                >
                  상세보기
                </Button>,
                <QRScan event={row} />,
                <Button
                  size="small"
                  variant="contained"
                  color="secondary"
                  onClick={handleReportOpen}
                >
                  소감문 확인
                </Button>,
              ];
            },
          },
        ]}
        disableColumnMenu
        disableDensitySelector
        // disableSelectionOnClick
        hideFooterSelectedRowCount
        // loading
        // checkboxSelection
      />
      <ReportDialog open={reportOpen} onClose={handleReportClose} />
    </>
  );
}
