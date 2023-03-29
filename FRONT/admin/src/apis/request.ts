import axios from 'axios';

export const getAllRequests = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/admin/requests`);
  return response.data;
};

export const getPendingRequests = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/admin/requests/status/1`);
  return response.data;
};

export const acceptRequest = async (id: number, duration: string) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/admin/requests/accept`, {
    id,
    duration,
  });
  return response.data;
};

export const declineRequest = async (id: number, message: string) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/admin/requests/decline`, {
    id,
    response: message,
  });
  return response.data;
};
