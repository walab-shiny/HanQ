import axios from 'axios';
import { IQrRequest } from '../types/qr';

export const sendQrRequest = async (data: IQrRequest) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/attend`, data);
  return response;
};
