import AWS from 'aws-sdk';
import axios from 'axios';
import { IEvent } from '../types/event';
import uuid from 'react-uuid';

export const getEventList = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/event/list`, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IEvent[];
};

export const getEvent = async (id: number) => {
  const response = await axios({
    url: `${process.env.REACT_APP_SERVER}/api/event/${id}`,
    method: 'get',
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response.data as IEvent;
};

export const addEvent = async (data: IEvent) => {
  const reqData = { ...data, tags: data.tags.map((tag) => tag.id) };
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/event`, reqData, {
    headers: { Authorization: localStorage.getItem('token') },
  });
  return response;
};

export const addEventWithFile = async (data: IEvent, file: File) => {
  AWS.config.update({
    accessKeyId: process.env.REACT_APP_S3_ACCESS_KEY,
    secretAccessKey: process.env.REACT_APP_S3_SECRET_ACCESS_KEY,
  });
  const uploadBucket = new AWS.S3({
    params: { Bucket: process.env.REACT_APP_S3_BUCKET },
    region: process.env.REACT_APP_S3_REGION,
  });

  const imageRef = `upload/${uuid()}-${file.name}`;
  const params = {
    ACL: 'public-read',
    Body: file,
    Bucket: process.env.REACT_APP_S3_BUCKET,
    Key: imageRef,
    ContentType: 'image/jpeg',
  };

  return uploadBucket
    .putObject(params as any)
    .on('success', async () => {
      const image = `${process.env.REACT_APP_S3_STORAGE}/${imageRef}`;
      const reqData = { ...data, image, tags: data.tags.map((tag) => tag.id) };
      const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/event`, reqData, {
        headers: { Authorization: localStorage.getItem('token') },
      });
      return response;
    })
    .send((err) => {
      if (err) console.log(err);
    });
};
