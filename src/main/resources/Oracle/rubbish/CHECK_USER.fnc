create or replace function CHECK_USER(p_user_name in varchar2) return boolean is
  Result boolean;
  i_count number := 0;
begin
  select count(*) into i_count from USERS u
  where u.user_name = p_user_name;
  IF (i_count > 0) then 
    Result := true;
  else
    Result := false; 
  end if; 
  return(Result);
end CHECK_USER;
/