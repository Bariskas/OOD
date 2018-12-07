#pragma once


class CPictureDraft
{
public:
	typedef std::vector<std::unique_ptr<CShape>> Storage;

	CPictureDraft() {};
private:
	Storage m_shaped;
};