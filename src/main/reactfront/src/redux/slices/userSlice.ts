import axios, { AxiosError } from 'axios';
import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';
import HttpError from '../../service/httpError';
import {
  UserFilteringData,
  UserListInitialState,
  UserListPayload,
} from '../../model/interface/userList';

interface ThunkArg {
  page: number;
  submitData?: UserFilteringData;
}

export const getList = createAsyncThunk<UserListPayload, ThunkArg>(
  'user/GET_USER',
  async ({ page = 0, submitData }, thunkAPI: any) => {
    try {
      const response = await (
        await axios.get('/userMemberList', {
          params: {
            page,
            address: submitData?.address,
            year: submitData?.year,
            month: submitData?.month,
            day: submitData?.day,
            gender: submitData?.gender,
            name: submitData?.name,
            phone1: submitData?.phone1,
            phone2: submitData?.phone2,
            phone3: submitData?.phone3,
            signDateStart: submitData?.signDateStart,
            signDateEnd: submitData?.signDateEnd,
            modifyDateStart: submitData?.modifyDateStart,
            modifyDateEnd: submitData?.modifyDateEnd,
            status: submitData?.status,
            email: submitData?.email,
            saveStatus: submitData?.saveStatus,
          },
        })
      ).data;
      return response;
    } catch (error) {
      const { response } = error as unknown as AxiosError;
      if (response) {
        const errorMessage = thunkAPI.rejectWithValue({ errorMessage: response.data });
        const statusCode = thunkAPI.rejectWithValue(response.data).payload.status;
        throw new HttpError(statusCode, errorMessage).errorMessage;
      } else {
        console.error('Unknown Error');
      }
    }
  },
);

const initialState: UserListInitialState = {
  payload: undefined,
  submitData: undefined,
  loading: false,
  error: undefined,
  page: 0,
};

export const userSlice = createSlice({
  name: 'userList',
  initialState,
  reducers: {
    handlePage(state, action: PayloadAction<number>) {
      state.page = action.payload;
    },
    handleSubmit(state, action: PayloadAction<UserFilteringData>) {
      state.submitData = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(getList.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(getList.fulfilled, (state, action: PayloadAction<UserListPayload>) => {
      state.loading = false;
      state.payload = action.payload;
    });
    builder.addCase(getList.rejected, (state, action) => {
      state.loading = false;
      state.error = action.error.message;
    });
  },
});
