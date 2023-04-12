export const getDisplayTime = (time: string) => {
  return time.split('T')[0] + ' ' + time.split('T')[1];
};
