export interface CodeEmail {
  codeGroup: string;
  codeName: string;
  codeUser: string;
  codeValue: string;
  no: number;
  reg_Date: Date;
  update_Date: Date;
}

export interface UserInfo {
  accounts?: any;
  address?: string;
  address1?: string;
  address2?: string;
  email?: string;
  gender?: string;
  id?: string;
  jobkey?: string;
  modifyDate?: Date;
  name?: string;
  no?: number;
  password?: string;
  phone1?: string;
  phone2?: string;
  phone3?: string;
  reg_Date?: Date;
  resCnt?: string;
  saveStatus?: string;
  signDate?: string;
  ssn?: string;
  ssn1?: string;
  ssn2?: string;
  update_Date?: Date;
}

export interface PageInfo {
  startPage: number;
  currPage: number;
  totalPage: number;
  endPage: number;
}

export interface UserFilteringData {
  address?: string;
  year?: string;
  month?: string;
  day?: string;
  gender?: string;
  name?: string;
  phone1?: string;
  phone2?: string;
  phone3?: string;
  signDateStart?: string;
  signDateEnd?: string;
  modifyDateStart?: string;
  modifyDateEnd?: string;
  status?: string;
  email?: string;
  saveStatus?: string;
}

export type UserListPayload = [CodeEmail[], UserInfo[], PageInfo];

export interface UserListInitialState {
  payload?: UserListPayload;
  submitData?: UserFilteringData;
  loading: boolean;
  error?: string;
  page: number;
}
