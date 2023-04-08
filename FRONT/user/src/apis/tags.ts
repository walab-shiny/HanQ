import axios from 'axios';

export const getTagList = async () => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/tag`);
  return response;
};
