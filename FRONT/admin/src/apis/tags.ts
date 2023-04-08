import axios from 'axios';

export const getTags = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/tag`);
  return response.data;
};

export const addTag = async (name: string) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/tag`, { name });
  return response.data;
};

export const updateTag = async (id: number, name: string) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/tag/update`, { id, name });
  return response.data;
};

export const deleteTag = async (id: number) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/tag/delete`, { id });
  return response.data;
};
