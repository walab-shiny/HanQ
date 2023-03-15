import axios from 'axios';

export const loginWithCredential = async (credential: string) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/user`, {
    credential: credential,
  });
  return response.data;
};

export const getUserInfoByUserId = async (userId: number) => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/user/${userId}`);
  return response.data;
};
