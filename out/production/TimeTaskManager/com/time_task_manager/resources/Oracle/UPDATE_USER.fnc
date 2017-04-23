create or replace function UPDATE_USER(p_user_name in varchar2, p_password in varchar2, p_user_description in varchar2 ) return boolean is
  Result boolean;
begin
  
  if (check_user(p_user_name) <> false) then
    update users u set u.password = p_password, u.user_description = p_user_description
    where u.user_name = p_user_name;
    Result := true;
  else
    Result := false;
  end if;     
  
  return(Result);
end UPDATE_USER;
/
