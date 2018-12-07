#pragma once

class IDesigner
{
public:
	virtual ~IDesigner() {}
	virtual CPictureDraft CreateDraft(IOstream) = 0;
};

class CDesignder : public IDesigner
{

};