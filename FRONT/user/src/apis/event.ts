import axios from 'axios';
import { IEvent } from '../types/event';

export const getEventList = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/event/list`, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IEvent[];
};

export const addEvent = async (data: IEvent) => {
  const response = await axios.post(
    `${process.env.REACT_APP_SERVER}/api/event`,
    { ...data, tags: [1, 2] },
    {
      headers: { Authorization: localStorage.getItem('token') },
    },
  );
  return response.data;
};
