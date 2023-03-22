import axios from 'axios';

export const loginWithCredential = async (credential: string) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/user`, {
    credential: credential,
  });
  return response.data;
};

export const studentRegister = async (userId: number, studentNum: number, departmentId: number) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/user/student`, {
    userId,
    studentNum,
    departmentId,
  });
  return response.data;
};

export const otherRegister = async (userId: number, affiliation: string) => {
  const response = await axios.post(`${process.env.REACT_APP_SERVER}/api/user/other`, {
    userId,
    affiliation,
  });
  return response.data;
};

export const getUserInfoByUserId = async (userId: number) => {
  const response = await axios.get(`${process.env.REACT_APP_SERVER}/api/user/${userId}`);
  return response.data;
};
