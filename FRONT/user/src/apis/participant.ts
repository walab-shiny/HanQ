import axios from 'axios';
import { IParticipant } from '../types/participant';
import { IEvent } from '../types/event';

export const getParticipantList = async (id: string) => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/user/attend/${id}`, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IParticipant[];
};

export const getUserParticipateList = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/event/attended`, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IEvent[];
};
