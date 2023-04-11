import axios from 'axios';
import { Iparticipant } from '../types/participant';
import { IEvent } from '../types/event';

export const getParticipantList = async (id: string) => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/user/attend/${id}`, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as Iparticipant[];
};

export const getUserParticipantList = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/user/attended`, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IEvent[];
};
