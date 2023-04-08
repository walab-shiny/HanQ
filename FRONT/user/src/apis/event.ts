import axios from 'axios';
import { IEvent } from '../types/event';

export const getEventList = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/event/list`, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IEvent[];
};

export const addEvent = async (data: IEvent) => {
  const reqData = { ...data, tags: data.tags.map((tag) => tag.id) };
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/event`, reqData, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data;
};

export const getEvent = async (id: number) => {
  const response = await axios({
    url: `${process.env.REACT_APP_SERVER}/api/event/${id}`,
    method: 'get',
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IEvent;
};
