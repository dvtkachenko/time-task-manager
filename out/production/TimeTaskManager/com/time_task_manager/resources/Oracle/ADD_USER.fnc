create or replace function ADD_USER(p_USER_NAME in varchar2, p_PASSWORD in varchar2, p_USER_DESCRIPTION in varchar2) return boolean is
  Result boolean;
begin
  if (check_user(p_USER_NAME) <> TRUE) THEN 
    insert into users 
    values (seq_users.nextval,p_user_name, p_password, p_user_description);
    Result := true;
  else
    Result := false;
  end if;  
  return(Result);
end ADD_USER;
/
