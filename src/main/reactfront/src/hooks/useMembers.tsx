import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { UserFilteringData, UserListInitialState } from '../model/interface/userList';
import { getList } from '../redux/slices/userSlice';
import { RootState, useAppDispatch } from '../redux/store';

const useMembers = () => {
  const dispatch = useAppDispatch();
  const userList = useSelector<RootState, UserListInitialState>((state) => state.userList);

  interface ThunkArg {
    page: number;
    submitData?: UserFilteringData;
  }

  const userListArgs: ThunkArg = {
    page: userList.page,
    submitData: userList.submitData,
  };

  useEffect(() => {
    dispatch(getList(userListArgs));
  }, [userList.page, userList.submitData]);

  return {
    users: userList.payload?.[1],
    loading: userList?.loading,
    error: userList?.error,
    PageInfo: userList.payload?.[2],
    emailCodeGroup: userList.payload?.[0],
  };
};
export default useMembers;
